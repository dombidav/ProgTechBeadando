package com.hl5u4v.progtech.core.ErrorHandling;

import com.hl5u4v.progtech.core.Helpers.FileManager;
import com.hl5u4v.progtech.views.ErrorView;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FatalError {
    public static void Crash(int code, String message, Throwable e) {
        new ErrorView(code, message).show();
        writeErrorLog(code, message, e);
        System.exit(code);
    }

    private static void writeErrorLog(int code, String message, @NotNull Throwable e) {
        writeLog(code, message);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuuMMdd'_'HHmmss");
        var fileName = LocalDateTime.now().format(f);
        var content = new ArrayList<String>();
        content.add(String.format("FATAL ERROR (%s: \"%s\") AT %s", code, message, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        content.add(String.format("EXCEPTION MESSAGE: \"%s\"", e.getMessage()));
        content.add("");
        content.add("-------------------------------------------");
        content.add("");
        content.add("StackTrace: ");
        for (var item : e.getStackTrace()) {
            content.add(item.toString());
        }
        FileManager.writeLines("log/reports", String.format("%s.log", fileName), content.toArray());
    }

    private static void writeLog(int code, String message) {
        DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        var s = String.format("%s;;%s;;%s", LocalDateTime.now().format(f), code, message);
        FileManager.writeFile("log", "errors.log", s, true);
    }
}
