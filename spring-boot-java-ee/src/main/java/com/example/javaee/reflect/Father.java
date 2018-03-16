package com.example.javaee.reflect;

public class Father {
    public String superName;
    private String interest;

    public Father() {
    }

    public Father(String superName, String interest) {
        this.superName = superName;
        this.interest = interest;
    }

    public void superMethod1() {
        System.out.println("调用了：公有的superMethod1方法无参");
    }
    protected void superMethod2(String name) {}
    void superMethod3(String interest) {}
    private void superMethod4() {
        System.out.println("调用了：私有的superMethod4方法无参");
    }
}
