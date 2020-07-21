package com.lsl.dsp.factory.abstractfactory;

public class WindowsFormFactory implements IFormFactory {

    @Override
    public IButton createButton() {
        return new WindowsButton();
    }

    @Override
    public IText createText() {
        return new WindowsText();
    }

}
