package aethernia;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[20];

    public Sound() {

        soundURL[0] = getClass().getResource("/sounds/WoW-ThunderBrew.wav");
        soundURL[1] = getClass().getResource("/sounds/PowerUp.wav");
        soundURL[2] = getClass().getResource("/sounds/DoorOpen1.wav");
        soundURL[3] = getClass().getResource("/sounds/KeyPick.wav");
        soundURL[4] = getClass().getResource("/sounds/End.wav");
    }

    public void setFile(int i) {
        try{
            AudioInputStream AIS = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(AIS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
