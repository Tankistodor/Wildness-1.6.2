package wildness.mod.gui;

import org.lwjgl.opengl.GL11;

import wildness.mod.core.WildnessTime;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiCalendar extends GuiScreen
{
    World world;
    int x;
    int z;
    EntityPlayer player;
    private static final ResourceLocation texture = new ResourceLocation("wildness:textures/gui/gui_calendar.png");
    protected int xSize = 176;
    protected int ySize = 184;
    protected int guiLeft;
    protected int guiTop;

    public GuiCalendar(EntityPlayer p, World world, int i, int j, int k)
    {
        this.world = world;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.x = i;
        this.z = k;
        this.player = p;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();

        //TODO REMOVE THEM
        //if (TFCOptions.enableDebugMode)
        /*{
            this.buttonList.clear();
            int l = (this.width - this.xSize) / 2;
            int i1 = (this.height - this.ySize) / 2;
            this.buttonList.add(new GuiButton(0, l + 20, i1 + 118, 66, 20, StringUtil.localize("gui.Calendar.1Hour")));
            this.buttonList.add(new GuiButton(1, l + 20, i1 + 137, 66, 20, StringUtil.localize("gui.Calendar.1Day")));
            this.buttonList.add(new GuiButton(2, l + 20, i1 + 156, 66, 20, StringUtil.localize("gui.Calendar.1Week")));
            this.buttonList.add(new GuiButton(3, l + 85, i1 + 118, 66, 20, StringUtil.localize("gui.Calendar.1Month")));
            this.buttonList.add(new GuiButton(4, l + 85, i1 + 137, 66, 20, StringUtil.localize("gui.Calendar.1Year")));
        }*/
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.mc.func_110434_K().func_110577_a(texture);
        int var4 = this.guiLeft;
        int var5 = this.guiTop;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
        this.drawCenteredString(this.fontRenderer, "Calendar", l + 87, i1 + 16, 16777215);
        this.drawCenteredString(this.fontRenderer, "Season" + " : " + WildnessTime.seasons[WildnessTime.getMonth()], l + 87, i1 + 26, 0);
        this.drawCenteredString(this.fontRenderer, "Day" + " : " + WildnessTime.Days[WildnessTime.getDayOfWeek()], l + 87, i1 + 36, 0);
        int dom = WildnessTime.getDayOfMonth();
        int month = WildnessTime.currentMonth;
        this.drawCenteredString(this.fontRenderer, "Date" + " : " + dom + " " + WildnessTime.months[month] + ", " + (1000 + WildnessTime.getYear()), l + 87, i1 + 46, 0);

        //float temp = (float)Math.round(TFC_Climate.getHeightAdjustedTemp((int)this.player.posX, (int)this.player.posY, (int)this.player.posZ));
        long h = WildnessTime.getHour();
        String hour = "";

        if (h == 0L)
        {
            hour = "WitchHour";
        }
        else
        {
            hour = hour + h;
        }

        this.drawCenteredString(this.fontRenderer, "Hour" + " : " + hour, l + 87, i1 + 56, 0);

        for (int var6 = 0; var6 < this.buttonList.size(); ++var6)
        {
            GuiButton var7 = (GuiButton)this.buttonList.get(var6);
            var7.drawButton(this.mc, par1, par2);
        }
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Renders the specified text to the screen, center-aligned.
     */
    public void drawCenteredString(FontRenderer fontrenderer, String s, int i, int j, int k)
    {
        fontrenderer.drawString(s, i - fontrenderer.getStringWidth(s) / 2, j, k);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton guibutton)
    {
        if (this.world.isRemote)
        {
            if (guibutton.id == 0)
            {
                ModLoader.getMinecraftInstance().thePlayer.sendChatMessage("/time add " + WildnessTime.dayLength / 24);
            }
            else if (guibutton.id == 1)
            {
                ModLoader.getMinecraftInstance().thePlayer.sendChatMessage("/time add " + WildnessTime.dayLength);
            }
            else if (guibutton.id == 2)
            {
                ModLoader.getMinecraftInstance().thePlayer.sendChatMessage("/time add " + WildnessTime.dayLength * 7);
            }
            else if (guibutton.id == 3)
            {
                ModLoader.getMinecraftInstance().thePlayer.sendChatMessage("/time add " + WildnessTime.dayLength * WildnessTime.daysInMonth);
            }
            else if (guibutton.id == 4)
            {
                ModLoader.getMinecraftInstance().thePlayer.sendChatMessage("/time add " + WildnessTime.dayLength * WildnessTime.daysInYear);
            }
        }
    }
}

