package persistencies;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import classes.Place;
import classes.Schedule;
import interfaces.IPersistence;

public class FileSerialization implements IPersistence
{
    @Override
    public List<Schedule> getPlanningen(Context context, String filename)
    {
        List<Schedule> schedules = new ArrayList<>();
        ObjectInputStream objectIn = null;
        Object object = null;
        try {
            FileInputStream fileIn = context.getApplicationContext().openFileInput(filename);
            objectIn = new ObjectInputStream(fileIn);
            schedules = (List<Schedule>) objectIn.readObject();

        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
        }

        return schedules;
    }

    @Override
    public void addPlanning(Context context, List<Schedule> schedule, String filename) {
        ObjectOutputStream objectOut = null;
        try {
            FileOutputStream fileOut = context.openFileOutput(filename, Activity.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(schedule);
            fileOut.getFD().sync();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
        }
    }

    @Override
    public void addPlace(Schedule schedule, Place place) { throw new UnsupportedOperationException(); }
}
