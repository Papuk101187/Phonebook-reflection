package org.example.menu;

import java.io.IOException;

public interface MenuItem {

    public void doAction() throws IOException, InterruptedException;
    public String getName();
    public boolean closeAfter();


}
