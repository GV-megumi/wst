import com.alibaba.fastjson.JSONArray;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 读取latlon.json文件中的经纬度信息
        String jsonStr = readFile("latlon.json");
       // System.out.println(jsonStr);

        JSONArray polygons =(JSONArray)JSONArray.parse(jsonStr);
       // System.out.println(polygons.size());
       // JSONArray pointArr = (JSONArray) polygons.get(1);
       // System.out.println(pointArr.get(1));


        // 构造多边形对象
        List<Point> points = new ArrayList<>();
        for (int i=0;i<polygons.size();i++) {
            JSONArray pointArr = (JSONArray) polygons.get(i);
            double lng = pointArr.getDoubleValue(0);
            double lat = pointArr.getDoubleValue(1);
         //   System.out.println("["+lng+","+lat+"]");
            Point point = new Point(lng, lat);
            points.add(point);
        }

        Polygon polygon = new Polygon(points);
        // 判断p1、p2、p3、p4是否在多边形内部
        Point p1 = new Point(119.037089, 32.259867);
        Point p2 = new Point(118.821489, 32.077388);
        Point p3 = new Point(118.80657099, 32.0353893);
        Point p4 = new Point(118.894173, 32.077862);
        System.out.println("p1 is in polygon: " + polygon.contains(p1));
        System.out.println("p2 is in polygon: " + polygon.contains(p2));
        System.out.println("p3 is in polygon: " + polygon.contains(p3));
        System.out.println("p4 is in polygon: " + polygon.contains(p4));



    }


    // 读取文件内容并返回字符串
    private static String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}


// 经纬度点类
class Point {
    public double lng;
    public double lat;
    public Point(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
// 多边形类
class Polygon {
    public List<Point> points;
    public Polygon(List<Point> points) {
        this.points = points;
    }
    // 判断点是否在多边形内部, 射线法
    public boolean contains(Point point) {
        int crossings = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i+1) % points.size());
            if (((p1.lat <= point.lat && point.lat < p2.lat) || (p2.lat <= point.lat && point.lat < p1.lat)) &&
                    (point.lng < (p2.lng - p1.lng) * (point.lat - p1.lat) / (p2.lat - p1.lat) + p1.lng)) {
                crossings++;
            }
        }
        return (crossings % 2 != 0);


    }


}