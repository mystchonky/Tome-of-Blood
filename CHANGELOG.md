# Changelog

## [0.3.1] - 2023-10-30

### Changed

- Removed the experience reduction of Heretic's Armor which caused upgrade tomes to be buggy
- Changed Mana Attunement discount calculation to fix 0 spell cost due to integer conversion

## [0.3.0] - 2023-10-28

Configs have changed. It is recommended to delete the old `tomeofblood` config and let it regenerate.

### Added

- Basic documentation in patchouli based `worn_notebook`

### Changed

- Reworked Tome of blood. It now lets you cast without mana limits. It uses mana first and then LP.
- Tome of blood textures have been updated!
- Living Mage Armor is now called Heretic's Armor
- Heretic's Armor now gains experience slower than its normal blood magic counterpart (exact scale configurable in
  configs)
- Default color for Heretic's Armor is now Red instead of Purple
- Mana Attunement trait has been rebalanced (Documentation has also been added)

## [0.2.1] - 2023-10-09

### Fixed

- Elytra upgrade now works with Living Mage Armor

## [0.2.0] - 2023-10-07

Configs have changed. It is recommended to delete the old `tomeofblood` config and let it regenerate.

### Changed

- Built against Neoforge. Requires newer versions of Ars Nouveau and Blood Magic
- Rebalanced Sentient Harm Glyph. Now it is more inline with base Harm Glyph but still has its special characteristics

### Fixed

- Sentient Harm Glyph causing server crash due to older version of Ars Nouveau

## [0.1.2] - 2023-09-30

### Added

- New textures for Living Mage Armor (Thanks to Gronglegrowth!)

### Fixes

- Fixed living armor modifiers not applying

## [0.1.1] - 2023-09-13

### Added

- recipes for Tome of Blood upgrades
- recipe of Living Mage Armor


## [0.1.0] - 2023-09-09

### Added

- Initial build
- 3 tiers/versions of Tome of Blood
- Glyph of Sentient Harm
- Mint tea item
- Living Armor Upgrade for Mana attunement
- Initial version of Living Mage Armor