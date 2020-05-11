package com.hl5u4v.progtech.app.views;

import com.hl5u4v.progtech.core.interfaces.IShow;
import org.jetbrains.annotations.NotNull;

public class Command_View implements IShow {
    @Override
    public void show() {
        System.out.println("Usable commands:");
        System.out.println("create");
        System.out.println("edit");
        System.out.println("delete");
        System.out.println("users");
        System.out.println("locks");
        System.out.println("allow / disallow");
    }

    public void show(@NotNull String command) {
        switch (command) {
            case "user":
                System.out.println("Working with users:\n\tusers\t->\tshow all users\n\tuser <user_id>\t->\tshow specific user\n\tcreate user\n\tedit user <lock_id>\n\tdelete user <lock_id>\n");
                break;
            case "lock":
                System.out.println("Working with locks:\n\tlocks\t->\tshow all locks\n\tlock <lock_id>\t->\tshow specific lock\n\tcreate lock\n\tedit lock <lock_id>\n\tdelete lock <lock_id>\n");
                break;
            case "disallow":
            case "allow":
                System.out.println("Working with rules:\n\tallow <user_id> <lock_id>\n\tdisallow <user_id> <lock_id>");
        }
    }
}
