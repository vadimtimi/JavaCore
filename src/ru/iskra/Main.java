package ru.iskra;

public class Main {
    static final int sizeArray = 10000000;

    public static void main(String[] args) {
        long timeNoThread = noThread();
        long timeYeshread = yesThread();

        System.out.println("Без потока: " + timeNoThread);
        System.out.println("В потоке: " + timeYeshread);
    }

    /**
     * Вычистение по формуле в основном потоке и заполнение массива
     * @return Возвращает время выполнения операции в миллисекундах
     */
    private static long noThread() {
        float[] arr = new float[sizeArray];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long timeStart = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long timeEnd = System.currentTimeMillis();

        return (timeEnd - timeStart);
    }

    private static long yesThread() {
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
                    a1[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        //Создание потока 2
        Thread t2 = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                for (int i = 0; i < a2.length; i++) {
                    a2[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
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

        return (timeEnd - timeStart);
    }
}
