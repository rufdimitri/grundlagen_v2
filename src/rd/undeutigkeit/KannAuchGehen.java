package rd.undeutigkeit;

public interface KannAuchGehen {
    default public void gehen() {
        System.out.println("KannAuchGehen.gehen()");
    };
}
