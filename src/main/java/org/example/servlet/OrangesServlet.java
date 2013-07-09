package org.example.servlet;

import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class OrangesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getOutputStream().println("Oranges");
        TrashRunnable run = new TrashRunnable(100, resp.getOutputStream());
        Thread mainThread = new Thread(run);
        mainThread.start();
        try {
            mainThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        resp.getOutputStream().println("Done");
    }
}

class TrashRunnable implements Runnable {

    private StringBuilder trash = new StringBuilder();
    private final float energy;
    private final ServletOutputStream out;

    TrashRunnable(float energy, ServletOutputStream out) {
        this.energy = energy;
        this.out = out;
    }

    @Override
    public void run() {
        if(energy<1) {
            return;
        }
        println(getName() + " Start");
        consumeMemory();

        TrashRunnable childA = new TrashRunnable(energy/2, out);
        TrashRunnable childB = new TrashRunnable(energy/2, out);
        Thread threadA = new Thread(childA);
        Thread threadB = new Thread(childB);
        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        println(getName() + " End");
    }

    private void println(String line) {
        try {
            synchronized (out) {
                out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getName() {
        return "Thread"+Thread.currentThread().getId();
    }

    private void consumeMemory() {
        for(int i=0; i<3000; i++) {
            trash.append("VAL:"+ i);
        }
        try {
            Thread.sleep(1300+(int)(100.*Math.random()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}