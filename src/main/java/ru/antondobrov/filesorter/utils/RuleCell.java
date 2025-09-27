package ru.antondobrov.filesorter.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import ru.antondobrov.filesorter.controllers.IDirectoryChooserService;
import ru.antondobrov.filesorter.controllers.IRuleService;
import ru.antondobrov.filesorter.controllers.RuleController;
import ru.antondobrov.filesorter.model.IRuleConfig;

public class RuleCell extends ListCell<IRuleConfig> {

    private VBox graphic;

    private final IRuleService ruleService;
    private final IDirectoryChooserService directoryChooserService;

    public RuleCell(IRuleService ruleService, IDirectoryChooserService directoryChooserService) {
        this.ruleService = ruleService;
        this.directoryChooserService = directoryChooserService;
    }

    @Override
    protected void updateItem(IRuleConfig ruleConfig, boolean empty) {
        RuleController controller;
        super.updateItem(ruleConfig, empty);

        if (empty || ruleConfig == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (graphic == null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(
                            "ru/antondovrov/filesorter/resources/fxml/rule-view.fxml"));

                    controller =
                            new RuleController(ruleConfig, ruleService, directoryChooserService);


                    loader.setController(controller);

                    graphic = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            setGraphic(graphic);
        }
    }
}
