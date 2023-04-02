import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;

public class TestLauncher {
    public static void main(String[] args) {
        var launcher = LauncherFactory.create(); //создаёт инстанс лаунчера, без параметров будет дефотл конфиг

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(DiscoverySelectors.selectPackage("app.service.impl")) //в селекторе можно задать класс или пакет
                .build();

        var summaryGeneratingListener = new SummaryGeneratingListener(); //лисенер собирает статистику по тестам
        launcher.execute(request, summaryGeneratingListener); //запускает сами тесты по реквесту с лисенерами

        summaryGeneratingListener.getSummary().printTo(new PrintWriter(System.out));

    }
}
