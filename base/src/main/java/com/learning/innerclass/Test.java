package com.learning.innerclass;

/**
 * 类详情
 *
 * @author bairenjie
 * @Date 2019/06/16
 */
public class Test {
    private static int num_static = num(1);

    public static int num(int num){
        System.out.println(num);
        return num;
    }
    public Test(){
        System.out.println("5");
    }
    static{
        System.out.println("2");
    }
    private int num = 2;
    {
        System.out.println("3");
    }

    public static void main(String[] args) {
        System.out.println("4");
        new TestChild();
        // 1 2 4 8 7 3 5 10 9
    }

   static  class TestChild extends Test{

       static{
           System.out.println("8");
       }

       private static int num = num(6);

       private int nu = 11;

        public static int num(int num){
            System.out.println("7");
            return num;
        }

        public TestChild(){
            System.out.println("9");
        }
       {
           System.out.println("10");
       }

    }

}
