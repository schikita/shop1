import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class PaymentProcessor {
    private ExecutorService executorService;

    public PaymentProcessor() {
        // Создаем пул потоков с фиксированным числом потоков
        this.executorService = Executors.newFixedThreadPool(5); // Например, 5 потоков
    }

    public void processPayment(Basket basket) {
        // Реализация асинхронного процесса оплаты
        PaymentTask paymentTask = new PaymentTask(basket);
        executorService.submit(paymentTask);
    }

    public void shutdown() {
        // Завершаем работу пула потоков после выполнения всех задач
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // Обработка ошибок при завершении работы пула потоков
        }
    }

    private class PaymentTask implements Runnable {
        private Basket basket;

        public PaymentTask(Basket basket) {
            this.basket = basket;
        }

        @Override
        public void run() {
            // Эмуляция процесса оплаты, например, задержка на 2 секунды
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Обработка ошибок при задержке
            }
            // Генерация случайного результата оплаты
            boolean paymentSuccess = Math.random() < 0.8; // 80% вероятность успешной оплаты

            // Уведомление магазина о результате оплаты
            notifyStore(paymentSuccess);
        }

        private void notifyStore(boolean paymentSuccess) {
            String statusMessage = paymentSuccess ? "Payment successful" : "Payment failed";
            System.out.println("Payment Status: " + statusMessage);
        }
    }
}
