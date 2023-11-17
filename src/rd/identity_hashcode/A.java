package rd.identity_hashcode;

public abstract class A  {
    public A() {
        System.out.format("Constructor A --- identityHashCode(hex): %s --- toString: %s \n"
                , Integer.toHexString(System.identityHashCode(this))
                , this.toString()
        );
    }
}
