import filter.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        JFrame frame = new JFrame("日志过滤器 by Ruby");
//        frame.setContentPane(new UI().panel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
//        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
//        frame.setLocation((screenWidth - frame.getWidth())/2, (screenHeight-frame.getHeight())/2);
//        frame.setVisible(true);

        File file = new File(System.getProperties().getProperty("user.dir") + "/src/log.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        StringBuilder sb = new StringBuilder();
        String last = "";
        boolean check = false;

        //初始化过滤器
        CommonFilter commonFilter = new CommonFilter();
//        TimeFilter timeFilter = new TimeFilter();
        LevelFilter levelFilter = new LevelFilter();
//        TagFilter tagFilter = new TagFilter();
        KeywordFilter keywordFilter = new KeywordFilter();

        //设置过滤器
//        timeFilter.setFrom(timeFilter.parseDate("11-07 16:38:09.537"));
//        timeFilter.setTo(timeFilter.parseDate("11-07 17:37:50.097"));
        levelFilter.setLevel(LevelFilter.Error);
//        tagFilter.setTag("AccountHttpManager");
        keywordFilter.setKeyword("AccountHttpManager");

        //添加过滤链
        FilterChain chain = new FilterChain();
//        chain.addFilter(commonFilter);
//        chain.addFilter(timeFilter);
        chain.addFilter(levelFilter);
//        chain.addFilter(tagFilter);
        chain.addFilter(keywordFilter);

        while ((line = bufferedReader.readLine()) != null) {
            if (line.length()>18 && line.contains("(")) {
                if (check) {
                    if (last.equals(line.substring(0, line.indexOf('(')))) {
                        String[] temp = line.split(":");
                        int index = temp[0].length() + temp[1].length() + temp[2].length() + 3;
                        for (int i = 0; i < index; ++i) {
                            sb.append(" ");
                        }
                        sb.append(line.substring(index) + "\n");
                        check = true;
                    } else {
                        check = false;
                    }
                } else {
                    if (chain.filtrate(line)) {
                        last = line.substring(0, line.indexOf("("));
                        sb.append(line + "\n");
                        check = true;
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
