/**
 * 
 */
package me.acf.FullPvP;

import java.util.ArrayList;

import net.citizensnpcs.api.trait.trait.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import me.acf.FullPvP.scoreboard.Scoreboard;
import me.acf.servidor.Servidor;
import me.hub.Main;
import me.hub.MiniPlugin;
import me.hub.API.Chat;
import me.hub.API.Util.UtilInv;
import me.hub.API.Util.UtilServer;
import me.hub.API.Util.UtilSound;
import me.hub.API.Util.Sound.Sounds;
import me.site.account.Account;
import me.site.account.AccountWeb;

/**
 * @author adriancf
 *
 */
public class Entrar extends MiniPlugin {

	/**
	 * @param moduleName
	 * @param plugin
	 */
	public Entrar(JavaPlugin plugin) {
		super("Kit-PvP Entrar", plugin);
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Player> joins = new ArrayList<>();
	public int evento = 100;
	
	  public static void RemoveItemBUG(Player p)
	  {
		  PlayerInventory inv = p.getInventory();
		  inv.remove(Material.COMMAND);
		  inv.remove(Material.COMMAND_CHAIN);
		  inv.remove(Material.COMMAND_MINECART);
		  inv.remove(Material.COMMAND_REPEATING);
	  }
	
	@EventHandler
	public void Entrar(final PlayerJoinEvent event)
	{
		event.setJoinMessage(null);
		Chat.ActionBar(event.getPlayer(), "§e§l" + "SEJA BEM-VINDO AO FULLPVP DO PLANETACRAFT_BR");
		 event.getPlayer().sendMessage(" ");
		 event.getPlayer().sendMessage("      §f§lSEJA BEM-VINDO; §7§l" + event.getPlayer().getName());
		 event.getPlayer().sendMessage("                 §f§lAO §a§l"+Bukkit.getServer().getServerName());
		 event.getPlayer().sendMessage(" ");
		 UtilSound.playSound(event.getPlayer(), Sounds.NOTE_PLING, 1, 0);
		 event.getPlayer().setFoodLevel(20);
		 event.getPlayer().setHealth(20);
		 Baltop.MostrarTOP(event.getPlayer());
	     Servidor.AddJoin();
	     if (event.getPlayer().getWorld().getName().equals("world")) {
	     event.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
	     }
	     event.getPlayer().setGameMode(GameMode.SURVIVAL);
	     UtilInv.restore(event.getPlayer());
	     System.out.print(AccountWeb.Conectar(Main.site + "/API/MG/FP.php?modo=ADD-conta&nick=" + event.getPlayer().getName()));
	     Conta.Loader(event.getPlayer());
	     Scoreboard.CriarScoreboard(event.getPlayer());
	     if (!joins.contains(event.getPlayer()))
	    {
	    	 Account.AddExp(event.getPlayer(), "10");
	    	joins.add(event.getPlayer());
	    	if (joins.size() == 666)
	    	{
	    		Bukkit.broadcastMessage("§c§l666 Súscipe ludio ludius " + event.getPlayer().getName());
	    		for (Player j : UtilServer.Jogadores()) {
	    			UtilSound.playSound(j, Sounds.AMBIENCE_THUNDER, 6.0F, 6.0F);
	    			Chat.ActionBar(event.getPlayer(), "§4§lO JOGADOR NUMERO 666 ENTROU (" + event.getPlayer().getName() + ")");
	    		
	    	}
	    	if (joins.size() == evento)
	    	{
	    		Bukkit.broadcastMessage("§5§lEVENTO §7Você acaba de receber §a+1 Chave.");
	    	    evento = evento+100;
	    		for (Player j : UtilServer.Jogadores()) {
	    		Account.AddChave(j, 1);
	    	    Account.UpdateAccount(j);
	    	    }
	    	    }
	    
	    	}
	    }
	     RemoveItemBUG(event.getPlayer());
	}



}
