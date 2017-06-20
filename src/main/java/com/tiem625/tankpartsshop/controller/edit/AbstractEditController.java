/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankpartsshop.controller.edit;

import com.tiem625.tankpartsshop.model.ContentProvider;
import com.tiem625.tankpartsshop.model.ContentWriteable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Anatolij
 * @param <T>
 */
public abstract class AbstractEditController<T extends ContentWriteable> implements ContentProvider {
    
    protected T value;
    
    @FXML
    protected AnchorPane rootPane;

    @Override
    public ContentWriteable getContentWriteable() {
        return value;
    }
    
    protected void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
    
    @FXML
    protected void handleCancel() {
        value = makeDefaultValue();
        closeWindow();
    }
    
    @FXML
    protected void handleOK() {
        value = makeValueFromFields();
        closeWindow();
    }

    protected abstract T makeDefaultValue();
    protected abstract T makeValueFromFields();
    
}
