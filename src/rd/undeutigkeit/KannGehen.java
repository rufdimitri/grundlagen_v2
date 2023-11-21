package rd.undeutigkeit;

public interface KannGehen {
    default public void gehen() {
        System.out.println("KannGehen.gehen()");
    };
}
