package rd.fractal_1;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PaintMe extends JFrame {
	private int width = 500, height = 500;
	private boolean isBackground = false;
	private Random random = new Random();
	ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	int threads = 2;  // threads: time {1:102s, 2:67s, 3:75s, 5:69s, 20:69s}
	ExecutorService executor = Executors.newFixedThreadPool(threads);
	List<RenderTask> renderTasks = new ArrayList<>();

	long lastSwitchTime = 0L;

	private BufferedImage graphicsContext;
	private JLabel contextRender;
	private JPanel contentPanel = new JPanel();

	double constantXMin = -0.61;
	double constantXMax = -0.5;
	double constantYMin = constantXMin;
	double constantYMax = constantXMax;
	double constantX = constantXMin;
	double constantY = constantYMin;
	double deltaX = 0.0001;
	double deltaY = 0.0001;
	Runnable swingInvokeTask = () -> SwingUtilities.invokeLater(this.renderCycle);
	Runnable renderCycle = () -> {
		if (lastSwitchTime == 0L) {
			lastSwitchTime = System.currentTimeMillis();
		}
		if (deltaX > 0 && constantX + deltaX > constantXMax || deltaX < 0 && constantX + deltaX < constantXMin) {
			deltaX = -deltaX;
			System.out.println(System.currentTimeMillis() - lastSwitchTime);
			lastSwitchTime = System.currentTimeMillis();
		}
		constantX += deltaX;

		if (deltaY > 0 && constantY + deltaY > constantYMax || deltaY < 0 && constantY + deltaY < constantYMin) {
			deltaY = -deltaY;
		}
		constantY += deltaY;
		render(new Point2D.Double(width, height), new Point2D.Double(constantX, constantY), 100);
		scheduledExecutor.schedule(this.swingInvokeTask, 1, TimeUnit.MILLISECONDS);
	};

	public PaintMe() {
		super("PaintMe Canvas");
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		graphicsContext = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		contextRender = new JLabel(new ImageIcon(graphicsContext));

		contentPanel.add(contextRender);

		contentPanel.setLayout(new CardLayout());
		this.setContentPane(contentPanel);
		this.pack();

		setVisible(true);

		for (int t = 0; t < threads; ++t) {
			renderTasks.add(new RenderTask());
		}
		renderCycle.run();
	}

	Point2D.Double computeNextJuliaMenge(Point2D.Double current, Point2D.Double constant) {
		// Zn²
		double zr = current.x * current.x - current.y * current.y;
		double zi = 2.0 * current.x * current.y;

		// Add constant
		return new Point2D.Double(zr + constant.x, zi + constant.y);
	}

	/**
	 * Returns the mod squared
	 *
	 * @param
	 */
	double mod2(Point2D.Double z) {
		return z.x * z.x + z.y * z.y;
	}

	/**
	 * Computes sequence elements until mod exceeds threshold or max iteration is reached
	 */
	int computeIterations(Point2D.Double z0, Point2D.Double constant, int maxIteration) {
		Point2D.Double zn = z0;
		int iteration = 0;
		while (mod2(zn) < 4.0 && iteration < maxIteration) {
			zn = computeNextJuliaMenge(zn, constant);
			// System.out.println("x = " + zn.x + " y = " + zn.y);
			iteration++;
		}
		// System.out.println("-----------------");
		return iteration;
	}

	/**
	 * Computes pixels color
	 */
	void render(Point2D.Double renderSize, Point2D.Double constant, int maxIterations) {
		this.setTitle("constX = " + constant.x + " constY = " + constant.y);

		// Compute the scale of the coordinates
		double scale = 0.5 / (renderSize.y / 2.0);
		int sizePerThread = (int) Math.round(renderSize.y) / threads;
		List<Future<?>> futures = new ArrayList<>();
		for (int t = 0; t < threads; ++t) {
			int yBegin = t * sizePerThread;
			int yEnd = (t + 1) * sizePerThread;
			if (t + 1 == threads) { // last thread takes the rest to the end (renderSize.y)
				yEnd = (int) Math.round(renderSize.y);
			}
			futures.add(executor.submit(
					renderTasks.get(t).setParams(this, yBegin, yEnd, renderSize, scale, constant, maxIterations)));
		}

		for (Future<?> future : futures) {
			try {
				future.get(); // get will block until the future is done
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			}
		}
		contextRender.repaint();
	}

	Color gradientGetColor(double gradientValue, double maxGradientValue) {
		int rgbPart = (int) Math.round(255 * 3 * gradientValue / maxGradientValue);
		int red = Math.min(rgbPart, 255);
		rgbPart -= red;
		int blue = Math.min(rgbPart, 255);
		rgbPart -= blue;
		int green = Math.min(rgbPart, 255);
		rgbPart -= green;

		return new Color(red, green, blue);
	}

	int graphicX(int x) {
		// return x + width/2;
		return x;
	}

	int graphicY(int y) {
		return height - y;
		// return height/2 - y;
	}

	void setPixelColor(int x, int y, Color color) {
		Graphics2D g2d = (Graphics2D) graphicsContext.getGraphics();
		int gX = graphicX(x);
		int gY = graphicY(y);
		g2d.setColor(color);
		g2d.drawLine(gX, gY, gX, gY);
	}

	void paintBackground(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	void paintOval(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(100, 200, 40, 60);
	}

}
