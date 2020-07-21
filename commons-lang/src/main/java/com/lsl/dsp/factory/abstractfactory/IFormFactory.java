package com.lsl.dsp.factory.abstractfactory;

public interface IFormFactory {
    IButton createButton();

    IText createText();
}
