package rd.undeutigkeit;

public class Creature implements KannGehen, KannAuchGehen {

    @Override
    public void gehen() {
        KannGehen.super.gehen();
    }
}
