package com.github.bubbletea14.ocular.ocular.Classes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.github.bubbletea14.ocular.ocular.services.MetricsCollection.MetricCollector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemTrayManager {
    
   private final MetricCollector metricCollector;
   private static final Logger logger = LoggerFactory.getLogger(MetricCollector.class);

    public SystemTrayManager(MetricCollector metricCollector) {
        this.metricCollector = metricCollector;
    }

    public void createSystemTrayIcon() {
        System.setProperty("java.awt.headless", "false");
        if (SystemTray.isSupported()) {
            SystemTray systemTray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\OcularIcon.jpeg");
            PopupMenu popupMenu = new PopupMenu();
            popupMenu.add(getExitItem());
            TrayIcon trayIcon = new TrayIcon(image, "Ocular", popupMenu);
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                logger.info("SystemTray could not be added.");
            }
        } else {
            logger.info("SystemTray is not supported.");
        }
        logger.info("SystemTray is added.");
    }

    private MenuItem getExitItem() {
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metricCollector.stopCollectingMetrics();
                System.exit(0);
            }
        });
        return exitItem;
    }
}
