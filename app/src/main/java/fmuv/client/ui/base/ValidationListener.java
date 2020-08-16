package fmuv.client.ui.base;

public interface ValidationListener {

    void onValidationError(int propertyId);
    void onEmpty(int propertyId);

}
