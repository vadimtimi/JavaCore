package ru.iskra;

public class Main {
    static final int sizeArray = 10000000;
    //static final int sizeArray = 10000000;

    public static void main(String[] args) {
        //
        float[] arr1 = noThread();
        //
        float[] arr2 = yesThread();

        try {
            float[] arr3 =  multiThread(10);
            // Сравнение массивов
            for(int i = 0; i < sizeArray; i ++) {
                if(arr1[i] != arr2[i]) {
                    System.out.println("Массивы arr1 и arr2 не равны");
                    break;
                }
            }
            // Сравнение массивов
            for(int i = 0; i < sizeArray; i ++) {
                if(arr1[i] != arr3[i]) {
                    System.out.println("Массивы arr1 и arr3 не равны");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Вычистение по формуле в основном потоке и заполнение массива
     * @return Возвращает время выполнения операции в миллисекундах
     */
    private static float[] noThread() {
        float[] arr = new float[sizeArray];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long timeStart = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long timeEnd = System.currentTimeMillis();


        System.out.println(("Без потоков: " + (timeEnd-timeStart)));

        return arr;
    }

    private static float[] yesThread() {
        float[] arr = new float[sizeArray];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long timeStart = System.currentTimeMillis();

        int h = sizeArray/2;
        float[] a1 = new float[h];
        float[] a2= new float[h];

        //Пример деления одного массива на два:
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        //long timeEnd = System.currentTimeMillis();

        //Создание потока 1
        Thread t1 = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                for (int i = 0; i < a1.length; i++) {
                    a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        //Создание потока 2
        Thread t2 = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                for (int i = 0; i < a2.length; i++) {
                    a2[i] = (float)(a2[i] * Math.sin(0.2f + (i+h) / 5) * Math.cos(0.2f + (i+h) / 5) * Math.cos(0.4f + (i+h) / 2));
                }
            }
        });
        //Запуск потока 1
        t1.start();
        //Запуск потока 2
        t2.start();
        //Ожидание завершения потока 1
        try { t2.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        //Ожидание завершения потока 2
        try { t1.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        //Пример обратной склейки:
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        long timeEnd = System.currentTimeMillis();

        System.out.println(("Два потока с копированием: " + (timeEnd-timeStart)));

        return arr;
    }

    private static float[] multiThread(int nThread) throws Exception {

        if(( sizeArray % nThread ) != 0) {
            throw new Exception("Нет возможности создать столько потоков.");
        }
        float[] arr = new float[sizeArray];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long timeStart = System.currentTimeMillis();

        int h = sizeArray/nThread;
        int[] startPos = new int[nThread];
        for(int i= 0; i < nThread; i++) {
            if(i == 0) {
                startPos[i] = 0;
            } else  {
                startPos[i] = (i*h);
            }
        }

        class MathThread extends Thread {
            public int start_;

            public MathThread(int start) {
                this.start_ = start;
            }
        }

        Thread[] threadsArray = new Thread[nThread];

        for( int i= 0; i < nThread; i++) {
            threadsArray[i] = new MathThread(i) {
                public void run() {
                    for(int j = 0; j < h; j++) {
                        //System.out.println((this.start_* h) + j);
                        int i = (this.start_* h) + j;
                        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                    }
                }
            };
        }

        for(int i = 0; i <nThread; i ++ ) {
            threadsArray[i].start();
        }

        for(int i = 0; i <nThread; i ++ ) {
            threadsArray[i].join();
        }

        long timeEnd = System.currentTimeMillis();

        System.out.println(("Поток без копирования: " + (timeEnd-timeStart)));

        return arr;
    }


}
