package org.example;

import org.example.motivation.controller.MotivationController;
import org.example.system.controller.SystemController;

public class App {

    public void run() {
        System.out.println("== motivation 실행 ==");

        SystemController systemController = new SystemController();
        MotivationController motivationController = new MotivationController();

        while (true) {
            System.out.print("명령어 ) ");
            String cmd = Container.getSc().nextLine().trim();

            if (cmd.equals("exit")) {
                systemController.exit();
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어가 입력되지 않았음");
                continue;
            }

            if (cmd.equals("add")) {
                motivationController.add();
            } else if (cmd.equals("list")) {
                motivationController.list();
            } else if (cmd.startsWith("del ")) {
                motivationController.delete(cmd);
            } else if (cmd.startsWith("del?")) {
                motivationController.newDelete(cmd);
            }else {
                System.out.println("사용할 수 없는 명령어야");
            }
        }


    }
}

