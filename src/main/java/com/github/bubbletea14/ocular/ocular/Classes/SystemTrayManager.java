package com.github.bubbletea14.ocular.ocular.Classes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
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
        if (!SystemTray.isSupported()) {
            logger.info("SystemTray is not supported.");
            return;
        }

        PopupMenu popupMenu = new PopupMenu();
        popupMenu.add(getExitItem());

        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/OcularIcon.jpeg"));
        } catch (Exception e) {
            logger.error("Error loading icon image: " + e.getMessage());
        }
        TrayIcon trayIcon = new TrayIcon(image, "Ocular", popupMenu);
        trayIcon.setImageAutoSize(true);

        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            logger.info("SystemTray could not be added.");
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
