package rd.identity_hashcode;

public class B extends A_super {
    public B() {
        System.out.format("Constructor B --- identityHashCode(hex): %s --- toString: %s \n"
                , Integer.toHexString(System.identityHashCode(this))
                , this.toString()
        );
    }
}
