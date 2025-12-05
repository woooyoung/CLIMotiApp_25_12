package org.example.motivation.controller;

import org.example.Container;
import org.example.motivation.entity.Motivation;

import java.util.*;

public class MotivationController {

    int lastId = 0; // 몇 번까지 썼더라?
    List<Motivation> motivations = new ArrayList<>(); // motivation 저장소

    public void doAdd() {
        int id = lastId + 1;
        System.out.print("body : ");
        String body = Container.getSc().nextLine();
        System.out.print("source : ");
        String source = Container.getSc().nextLine();

        Motivation motivation = new Motivation(id, body, source);

        motivations.add(motivation);

        System.out.printf("%d번 motivation이 등록되었습니다\n", id);
        lastId++;
    }

    public void showList() {
        System.out.println("=".repeat(40));
        System.out.printf("   번호   /    source      /    body   \n");

        if (motivations.size() == 0) {
            System.out.println("등록된거 없음 xxxxx");
            return;
        }
        for (int i = motivations.size() - 1; i >= 0; i--) {
            Motivation motivation = motivations.get(i);

            if (motivation.getSource().length() > 7) {
                System.out.printf("   %d     /    %s   /    %s   \n", motivation.getId(), motivation.getSource().substring(0, 7) + "...", motivation.getBody());
                continue;
            }
            System.out.printf("   %d     /    %s     /    %s   \n", motivation.getId(), motivation.getSource(), motivation.getBody());
        }
        System.out.println("=".repeat(40));
    }

    public void doDelete(String cmd) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[1]);

        if (cmdBits.length == 1) {
            System.out.println("명령어 확인하고 다시 써");
            return;
        }
        Motivation foundMotivation = findById(id);

        if (foundMotivation == null) {
            System.out.println("해당 moti는 ArrayList에 없던데?");
            return;
        }

        motivations.remove(foundMotivation);
        System.out.println(id + "번 moti 삭제됨");
    }

    public void newDoDelete(String cmd) {
        Rq rq = new Rq(cmd);

        System.out.println("rq.getParams(\"id\") : " + rq.getParams("id"));

        int id = Integer.parseInt(rq.getParams("id"));

        Motivation foundMotivation = findById(id);

        if (foundMotivation == null) {
            System.out.println("해당 moti는 ArrayList에 없던데?");
            return;
        }

        motivations.remove(foundMotivation);
        System.out.println(id + "번 moti 삭제됨");

    }

    public void doEdit(String cmd) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[1]);

        if (cmdBits.length == 1) {
            System.out.println("명령어 확인하고 다시 써");
            return;
        }
        Motivation foundMotivation = findById(id);

        if (foundMotivation == null) {
            System.out.println("해당 moti는 ArrayList에 없던데?");
            return;
        }

        // 찾은 motivation의 인스턴스 변수에 접근
        System.out.println("body(기존) : " + foundMotivation.getBody());
        System.out.println("source(기존) : " + foundMotivation.getSource());

        // 수정사항 입력받기
        String newBody;
        String newSource;
        while (true) {
            System.out.print("body : ");
            newBody = Container.getSc().nextLine().trim();

            if (newBody.length() != 0) {
                break;
            }

            System.out.println("수정사항(body) 입력해");
        }
        while (true) {
            System.out.print("source : ");
            newSource = Container.getSc().nextLine().trim();

            if (newSource.length() != 0) {
                break;
            }

            System.out.println("수정사항(source) 입력해");
        }

        // 찾은 motivation의 인스턴스 변수 값 수정
        foundMotivation.setBody(newBody);
        foundMotivation.setSource(newSource);

        System.out.println(id + "번 moti 수정됨");
    }

    public void showDetail(String cmd) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[1]);

        if (cmdBits.length == 1) {
            System.out.println("명령어 확인하고 다시 써");
            return;
        }
        Motivation foundMotivation = findById(id);

        if (foundMotivation == null) {
            System.out.println("해당 moti는 ArrayList에 없던데?");
            return;
        }
        System.out.println("-- detail -- ");
        System.out.println("번호 : " + foundMotivation.getId());
        System.out.println("body : " + foundMotivation.getBody());
        System.out.println("source : " + foundMotivation.getSource());
    }

    // 명령어의 id와 일치하는 motivation 찾기
    private Motivation findById(int id) {
        for (Motivation motivation : motivations) {
            if (motivation.getId() == id) {
                return motivation;
            }
        }
        return null;
    }


}
