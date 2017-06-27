package com.github.kirikakis.chess.cli;

import java.util.ArrayList;
import java.util.List;
import com.beust.jcommander.Parameter;

public class ChessArgs {

    @Parameter(names = "-help", help = true, order = 0, hidden = true)
    public boolean help = false;

    @Parameter(names = "-start", description = "Start Position e.g. A1,B2,C3...", order = 1)
    public String startPosition;

    @Parameter(names = "-end", description = "End Position e.g. H8,G7,F6... ", order = 2)
    public String endPosition;

    @Parameter(names = "-maxMoves", description = "Max moves allowed", order = 3)
    public int maxMoves = 3;

}
