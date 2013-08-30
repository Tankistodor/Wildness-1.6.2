package wildness.mod.core;

import java.util.ArrayList;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings
{
    public static ArrayList<KeyBinding> keyBindingsList;
    public static ArrayList<Boolean> isRepeatingList;

    public static void addKeyBinding(String name, int value)
    {
        if (keyBindingsList == null)
        {
            keyBindingsList = new ArrayList();
        }

        keyBindingsList.add(new KeyBinding(name, value));
    }

    public static void addIsRepeating(boolean value)
    {
        if (isRepeatingList == null)
        {
            isRepeatingList = new ArrayList();
        }

        isRepeatingList.add(Boolean.valueOf(value));
    }

    public static KeyBinding[] gatherKeyBindings()
    {
        return (KeyBinding[])keyBindingsList.toArray(new KeyBinding[keyBindingsList.size()]);
    }

    public static boolean[] gatherIsRepeating()
    {
        boolean[] isRepeating = new boolean[isRepeatingList.size()];

        for (int x = 0; x < isRepeating.length; ++x)
        {
            isRepeating[x] = ((Boolean)isRepeatingList.get(x)).booleanValue();
        }

        return isRepeating;
    }
}

