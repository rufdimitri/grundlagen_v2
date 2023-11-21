package rd.identity_hashcode;

public abstract class A_super {
    public A_super() {
        System.out.format("Constructor A_super --- identityHashCode(hex): %s --- toString: %s \n"
                , Integer.toHexString(System.identityHashCode(this))
                , this.toString()
        );
    }
}
