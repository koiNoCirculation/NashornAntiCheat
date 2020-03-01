package com.github.nanamiarihara.nashornanticheat.network.server;

import com.github.nanamiarihara.nashornanticheat.config.ConfigHandlerServer;
import com.github.nanamiarihara.nashornanticheat.network.ExpectedResponseRegistry;
import com.github.nanamiarihara.nashornanticheat.network.client.PacketScriptResponse;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class ResponseHandler implements IMessageHandler<PacketScriptResponse, PacketScriptChallenge> {
    @Override public PacketScriptChallenge onMessage(PacketScriptResponse message, MessageContext ctx) {
        String name = ctx.getServerHandler().playerEntity.getDisplayName();
        String onKick = ConfigHandlerServer.getConfig().getMessageOnChallengeFail();
        if(!ExpectedResponseRegistry.validate(name, message.getHashResponse()))
            ((EntityPlayerMP)ctx.getServerHandler().playerEntity).playerNetServerHandler.kickPlayerFromServer(onKick);
        return null;
    }
}