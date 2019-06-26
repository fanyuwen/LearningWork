package com.learning.bottomlayer;

/**
 * @author fanyuwen
 * @date 2019/6/26 15:53
 * 描述jvm内存分布
 */
public class Memory {

    /**
     * JVM虚拟机栈
     * JVM栈是线程私有的,每个线程创建的同时都会创建JVM栈,
     * JVM栈中存放的为当前线程中局部基本类型的变量(java中定义的八种基本类型:boolean、char、byte、short、int、long、float、double)、
     * 部分的返回结果以及(栈帧)Stack Frame,非基本类型的对象在JVM栈上仅存放一个指向堆上的地址
     */
    void stack() {

    }

    /**
     * PC(程序计数器)
     * 程序计数器是用于存储每个线程下一步将执行的JVM指令,如该方法为native的,则程序计数器中不存储任何信息
     */
    void ProgramCounterRegister() {

    }

    /**
     * 虚拟机堆
     * 它是JVM用来存储对象实例以及数组值的区域,可以认为Java中所有通过new创建的对象的内存都在此分配,Heap中的对象的内存需要等待GC进行回收.
     * <p>
     * (1) 堆是JVM中所有线程共享的,因此在其上进行对象内存的分配均需要进行加锁,这也导致了new对象的开销是比较大的
     * <p>
     * (2) Sun Hotspot JVM为了提升对象内存分配的效率,对于所创建的线程都会分配一块独立的空间TLAB(Thread Local Allocation Buffer),
     * 其大小由JVM根据运行的情况计算而得,在TLAB上分配对象时不需要加锁,因此JVM在给线程的对象分配内存时会尽量的在TLAB上分配,
     * 在这种情况下JVM中分配对象内存的性能和C基本是一样高效的,但如果对象过大的话则仍然是直接使用堆空间分配
     * <p>
     * (3) TLAB仅作用于新生代的Eden Space,因此在编写Java程序时,通常多个小的对象比大的对象分配起来更加高效.
     */
    private String name;

    void heap() {

        //  ===========================================================
        //  =                                                         =
        //  =                                                         =
        //  ===========================================================
        //


        abstract class BaseHeap {
        }
        /*
         * 年轻代
         */
        abstract class YoungGeneration extends BaseHeap {
            /**
             * 伊甸园
             */
            class Eden extends YoungGeneration {
            }

            /**
             * 存活空间
             */
            abstract class SurvivorSpaces extends YoungGeneration {
                /**
                 * From Space
                 */
                class FROM extends SurvivorSpaces {
                }

                /**
                 * To Space
                 */
                class To extends SurvivorSpaces {
                }
            }
        }
        /*
         * 老年代
         */
        abstract class OldGeneration extends BaseHeap {
        }
    }

    /**
     * 方法区
     * (1)在Sun JDK中这块区域对应的为PermanetGeneration,又称为持久代.
     * <p>
     * (2)方法区域存放了所加载的类的信息(名称、修饰符等)、类中的静态变量、类中定义为final类型的常量、类中的Field信息、类中的方法信息,
     * 当开发人员在程序中通过Class对象中的getName、isInterface等方法来获取信息时,这些数据都来源于方法区域,同时方法区域也是全局共享的,
     * 在一定的条件下它也会被GC,当方法区域需要使用的内存超过其允许的大小时,会抛出OutOfMemory的错误信息.
     */
    void methodArea() {

    }

    /**
     * 本地方法栈
     * JVM采用本地方法栈来支持native方法的执行,此区域用于存储每个native方法调用的状态.
     */
    void nativeMethodStacks() {

    }

}
