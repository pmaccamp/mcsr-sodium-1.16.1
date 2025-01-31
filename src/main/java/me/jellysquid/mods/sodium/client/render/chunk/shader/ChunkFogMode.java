package me.jellysquid.mods.sodium.client.render.chunk.shader;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.SodiumClientMod;
import me.jellysquid.mods.sodium.client.gl.shader.ShaderConstants;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL20C;

import java.util.List;
import java.util.function.Function;

public enum ChunkFogMode {
    NONE(ChunkShaderFogComponent.None::new, ImmutableList.of()),
    LINEAR(ChunkShaderFogComponent.Linear::new, ImmutableList.of("USE_FOG", "USE_FOG_LINEAR")),
    EXP2(ChunkShaderFogComponent.Exp2::new, ImmutableList.of("USE_FOG", "USE_FOG_EXP2")),
    LINEAR_PLANAR(ChunkShaderFogComponent.Linear::new, ImmutableList.of("USE_FOG", "USE_FOG_LINEAR", "USE_FOG_PLANAR")),
    EXP2_PLANAR(ChunkShaderFogComponent.Exp2::new, ImmutableList.of("USE_FOG", "USE_FOG_EXP2", "USE_FOG_PLANAR"));

    private final Function<ChunkProgram, ChunkShaderFogComponent> factory;
    private final List<String> defines;

    ChunkFogMode(Function<ChunkProgram, ChunkShaderFogComponent> factory, List<String> defines) {
        this.factory = factory;
        this.defines = defines;
    }

    public Function<ChunkProgram, ChunkShaderFogComponent> getFactory() {
        return this.factory;
    }

    public List<String> getDefines() {
        return this.defines;
    }

    public void addConstants(ShaderConstants.Builder constants) {
        for (String define : this.defines) {
            constants.define(define);
        }
    }
}
