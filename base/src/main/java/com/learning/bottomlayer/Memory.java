package com.learning.bottomlayer;

/**
 * @author fanyuwen
 * @date 2019/6/26 15:53
 * ����jvm�ڴ�ֲ�
 */
public class Memory {

    /**
     * JVM�����ջ
     * JVMջ���߳�˽�е�,ÿ���̴߳�����ͬʱ���ᴴ��JVMջ,
     * JVMջ�д�ŵ�Ϊ��ǰ�߳��оֲ��������͵ı���(java�ж���İ��ֻ�������:boolean��char��byte��short��int��long��float��double)��
     * ���ֵķ��ؽ���Լ�(ջ֡)Stack Frame,�ǻ������͵Ķ�����JVMջ�Ͻ����һ��ָ����ϵĵ�ַ
     */
    void stack() {

    }

    /**
     * PC(���������)
     * ��������������ڴ洢ÿ���߳���һ����ִ�е�JVMָ��,��÷���Ϊnative��,�����������в��洢�κ���Ϣ
     */
    void ProgramCounterRegister() {

    }

    /**
     * �������
     * ����JVM�����洢����ʵ���Լ�����ֵ������,������ΪJava������ͨ��new�����Ķ�����ڴ涼�ڴ˷���,Heap�еĶ�����ڴ���Ҫ�ȴ�GC���л���.
     * <p>
     * (1) ����JVM�������̹߳����,��������Ͻ��ж����ڴ�ķ������Ҫ���м���,��Ҳ������new����Ŀ����ǱȽϴ��
     * <p>
     * (2) Sun Hotspot JVMΪ�����������ڴ�����Ч��,�������������̶߳������һ������Ŀռ�TLAB(Thread Local Allocation Buffer),
     * ���С��JVM�������е�����������,��TLAB�Ϸ������ʱ����Ҫ����,���JVM�ڸ��̵߳Ķ�������ڴ�ʱ�ᾡ������TLAB�Ϸ���,
     * �����������JVM�з�������ڴ�����ܺ�C������һ����Ч��,������������Ļ�����Ȼ��ֱ��ʹ�öѿռ����
     * <p>
     * (3) TLAB����������������Eden Space,����ڱ�дJava����ʱ,ͨ�����С�Ķ���ȴ�Ķ�������������Ӹ�Ч.
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
         * �����
         */
        abstract class YoungGeneration extends BaseHeap {
            /**
             * ����԰
             */
            class Eden extends YoungGeneration {
            }

            /**
             * ���ռ�
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
         * �����
         */
        abstract class OldGeneration extends BaseHeap {
        }
    }

    /**
     * ������
     * (1)��Sun JDK����������Ӧ��ΪPermanetGeneration,�ֳ�Ϊ�־ô�.
     * <p>
     * (2)�����������������ص������Ϣ(���ơ����η���)�����еľ�̬���������ж���Ϊfinal���͵ĳ��������е�Field��Ϣ�����еķ�����Ϣ,
     * ��������Ա�ڳ�����ͨ��Class�����е�getName��isInterface�ȷ�������ȡ��Ϣʱ,��Щ���ݶ���Դ�ڷ�������,ͬʱ��������Ҳ��ȫ�ֹ����,
     * ��һ������������Ҳ�ᱻGC,������������Ҫʹ�õ��ڴ泬��������Ĵ�Сʱ,���׳�OutOfMemory�Ĵ�����Ϣ.
     */
    void methodArea() {

    }

    /**
     * ���ط���ջ
     * JVM���ñ��ط���ջ��֧��native������ִ��,���������ڴ洢ÿ��native�������õ�״̬.
     */
    void nativeMethodStacks() {

    }

}
