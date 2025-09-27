package ru.antondobrov.filesorter.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import ru.antondobrov.filesorter.controllers.IDirectoryChooserService;
import ru.antondobrov.filesorter.controllers.IRuleService;
import ru.antondobrov.filesorter.controllers.RuleController;
import ru.antondobrov.filesorter.model.IRuleConfig;

/**
 * A custom {@link ListCell} designed to display and manage the UI for a single sorting rule.
 * <p>
 * This class correctly handles the "cell recycling" mechanism of JavaFX's {@code ListView}. It
 * serves as a bridge between the list view and the detailed view of an individual rule.
 */
public class RuleCell extends ListCell<IRuleConfig> {

    private VBox graphic;
    private RuleController controller;

    private final IRuleService ruleService;
    private final IDirectoryChooserService directoryChooserService;

    /**
     * Constructs a new RuleCell with the necessary services.
     * <p>
     * These services are passed to the {@link RuleController} upon its creation, enabling the UI
     * within the cell to be fully interactive and delegate actions correctly.
     *
     * @param ruleService The service for modifying a rule's list of patterns.
     * @param directoryChooserService The service for opening a directory selection dialog.
     */
    public RuleCell(IRuleService ruleService, IDirectoryChooserService directoryChooserService) {
        this.ruleService = ruleService;
        this.directoryChooserService = directoryChooserService;
    }

    /**
     * Called by the {@code ListView} to update the cell's content.
     * <p>
     * This method correctly implements the cell recycling pattern:
     * <ol>
     * <li>If the cell is empty or the item is null, its contents are cleared.</li>
     * <li>If the visual components (graphic and controller) have not been created yet, they are
     * loaded from FXML and cached. The controller is created without a specific data item.</li>
     * <li><b>Crucially, on every call with a valid item</b>, it invokes
     * {@link RuleController#setData(IRuleConfig)} on the cached controller, passing the new rule.
     * The controller is then responsible for updating its UI bindings to reflect the new data.</li>
     * </ol>
     * This approach avoids creating new objects on every scroll and correctly displays data in
     * reused cells.
     *
     * @param ruleConfig The sorting rule data item to display in this cell. Can be {@code null}.
     * @param empty A boolean flag indicating if the cell is empty and not representing any data.
     */
    @Override
    protected void updateItem(IRuleConfig ruleConfig, boolean empty) {
        super.updateItem(ruleConfig, empty);

        if (empty || ruleConfig == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (graphic == null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("/ru/antondobrov/filesorter/fxml/rule-view.fxml"));
                    controller = new RuleController(ruleService, directoryChooserService);
                    loader.setController(controller);
                    graphic = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            controller.setData(ruleConfig);
            setGraphic(graphic);
        }
    }
}
