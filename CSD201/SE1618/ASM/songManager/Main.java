public class Main {

    static MyList songList = new MyList();

    public static void main(String[] args) {
        createSorted();
        /* createUnsorted(); */

        System.out.println("Song List:");
        songList.display();

        System.out.println("Sorted? " + songList.isSorted());

        System.out.println();
        songList.deleteLowestRating();
        System.out.println("Song List after deleting lowest rating:");
        songList.display();

        System.out.println();
        songList.deleteLowestRating();
        System.out.println("Song List after deleting lowest rating:");
        songList.display();

        System.out.println();
        songList.deleteLowestRating();
        System.out.println("Song List after deleting lowest rating:");
        songList.display();

        System.out.println();
        songList.deleteLowestRating();
        System.out.println("Song List after deleting lowest rating:");
        songList.display();
    }

    public static void createSorted() {
        songList.add("Fantastic Track", "Skilled Musicians", 4.8);
        songList.add("Night Serenade", "The Moonlight Ensemble", 4.7);
        songList.add("Digital Symphony", "Electronic Sound Wizards", 4.5);
        songList.add("Metropolis Lights", "Urban Beats", 4.5);
        songList.add("Rock Legends", "Legendary Rockers", 4.2);
        songList.add("Sunset Melody", "The Beachcombers", 4.2);
        songList.add("Sabbath's Call", "Dark Metal Masters", 4.1);
        songList.add("Relaxing Grooves", "Cool Vibes", 3.9);
        songList.add("Starry Skies", "Sky Watchers", 3.9);
        songList.add("Purple Haze", "Psychedelic Jams", 3.8);
        songList.add("Jazz Fusion", "Jazz Innovators", 3.7);
        songList.add("Stormy Seas", "Shipwrecked Sailors", 3.2);
        songList.add("Waves of Tranquility", "Ocean Explorers", 3.2);
        songList.add("Misunderstood Melodies", "Up-and-Coming Artists", 1.5);
        songList.add("Gloomy Ballad", "Obscure Musicians", 1.2);
        songList.add("Awkward Serenade", "Inexperienced Artists", 1.2);
    }

    public static void createUnsorted() {
        songList.add("Digital Symphony", "Electronic Sound Wizards", 4.5);
        songList.add("Starry Skies", "Sky Watchers", 3.9);
        songList.add("Fantastic Track", "Skilled Musicians", 4.8);
        songList.add("Metropolis Lights", "Urban Beats", 4.5);
        songList.add("Rock Legends", "Legendary Rockers", 4.2);
        songList.add("Sunset Melody", "The Beachcombers", 4.2);
        songList.add("Misunderstood Melodies", "Up-and-Coming Artists", 1.5);
        songList.add("Gloomy Ballad", "Obscure Musicians", 1.2);
        songList.add("Night Serenade", "The Moonlight Ensemble", 4.7);
        songList.add("Sabbath's Call", "Dark Metal Masters", 4.1);
        songList.add("Relaxing Grooves", "Cool Vibes", 3.9);
        songList.add("Purple Haze", "Psychedelic Jams", 3.8);
        songList.add("Awkward Serenade", "Inexperienced Artists", 1.2);
        songList.add("Stormy Seas", "Shipwrecked Sailors", 3.2);
        songList.add("Waves of Tranquility", "Ocean Explorers", 3.2);
        songList.add("Jazz Fusion", "Jazz Innovators", 3.7);
    }
}