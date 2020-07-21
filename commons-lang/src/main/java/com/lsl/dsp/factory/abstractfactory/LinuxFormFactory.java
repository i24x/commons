package com.lsl.dsp.factory.abstractfactory;

public class LinuxFormFactory implements IFormFactory {

    @Override
    public IButton createButton() {
        return new LinuxButton();
    }

    @Override
    public IText createText() {
        return new LinuxText();
    }

}
