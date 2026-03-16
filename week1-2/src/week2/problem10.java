package week2;

import java.util.*;

class VideoData {
    String videoId;

    VideoData(String videoId) {
        this.videoId = videoId;
    }
}

class MultiLevelCache {

    private LinkedHashMap<String, VideoData> L1;
    private HashMap<String, VideoData> L2;
    private HashMap<String, VideoData> L3;

    private int L1Hits = 0;
    private int L2Hits = 0;
    private int L3Hits = 0;

    public MultiLevelCache() {

        L1 = new LinkedHashMap<String, VideoData>(10000, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, VideoData> eldest) {
                return size() > 10000;
            }
        };

        L2 = new HashMap<>();
        L3 = new HashMap<>();
    }

    public VideoData getVideo(String videoId) {

        if (L1.containsKey(videoId)) {
            L1Hits++;
            System.out.println("L1 Cache HIT (0.5ms)");
            return L1.get(videoId);
        }

        if (L2.containsKey(videoId)) {
            L2Hits++;
            System.out.println("L1 Cache MISS");
            System.out.println("L2 Cache HIT (5ms)");

            VideoData video = L2.get(videoId);
            L1.put(videoId, video);
            System.out.println("Promoted to L1");

            return video;
        }

        System.out.println("L1 Cache MISS");
        System.out.println("L2 Cache MISS");

        VideoData video = L3.get(videoId);

        if (video == null) {
            video = new VideoData(videoId);
            L3.put(videoId, video);
        }

        L3Hits++;
        System.out.println("L3 Database HIT (150ms)");

        L2.put(videoId, video);

        return video;
    }

    public void getStatistics() {

        int total = L1Hits + L2Hits + L3Hits;

        double l1Rate = total == 0 ? 0 : (L1Hits * 100.0 / total);
        double l2Rate = total == 0 ? 0 : (L2Hits * 100.0 / total);
        double l3Rate = total == 0 ? 0 : (L3Hits * 100.0 / total);

        System.out.println("L1 Hit Rate: " + l1Rate + "%");
        System.out.println("L2 Hit Rate: " + l2Rate + "%");
        System.out.println("L3 Hit Rate: " + l3Rate + "%");
    }
}

public class problem10 {

    public static void main(String[] args) {

        MultiLevelCache cache = new MultiLevelCache();

        cache.getVideo("video_123");
        cache.getVideo("video_123");
        cache.getVideo("video_999");

        cache.getStatistics();
    }
}