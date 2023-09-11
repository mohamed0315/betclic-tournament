package dto

import model.Player

fun Player.toDto() : PlayerDto =
    PlayerDto(
        pseudo = this.pseudo,
    )

fun PlayerDto.toPlayer(): Player =
    Player(
        pseudo = this.pseudo,
        score = 0
    )