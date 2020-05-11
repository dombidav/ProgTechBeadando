package com.hl5u4v.progtech.core.interfaces;

public interface IResourceManipulatorView extends IView {

    default void success() {
        System.out.printf("%s%nSuccessfully created record%s%n", IView.ANSI_GREEN, IView.ANSI_RESET);
    }


    default void fail(String message) {
        System.out.println("\n".concat(IView.ANSI_RED).concat(message).concat(IView.ANSI_RESET));
    }
}
