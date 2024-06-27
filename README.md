# DonkeyBorder Plugin

The **DonkeyBorder** plugin dynamically controls the world border based on the location of a donkey in your Minecraft server. The donkey has some special abilities:

1. **Invincibility**: The donkey can't take damage or die.
2. **Surface Teleportation**: The donkey always stays on the surface.
3. **Frost Walking**: When the donkey is near water, it turned into frosted ice. 🧊

## Installation

1. Download the latest release from the [Releases](https://github.com/ArielWy/DonkeyBorder/releases) page.
2. Place the JAR file in your server's `plugins` folder.
3. Restart your server.

## Usage

- **Setting the World Border**: To activate the world border behavior, use the `/setborder` command. If the donkey already exists, it will teleport to your location. Alternatively, you can use `/donkeyborder setborder`. (Requires `donkeyborder.admin` permission.)

- **Teleporting to the Donkey**: If the donkey exists, you can teleport to it using `/tpdonkey` or `/donkeyborder tpdonkey`. (Also requires `donkeyborder.admin` permission.)

## Configuration

Customize the plugin behavior by editing the `config.yml` file:

```yaml
# config.yml
General:
  UpdateDelayTicks: 20  # Check and update frequency (in ticks)
  BorderRadius: 20.0    # Border radius per player (multiplied by online player count)
  IcePathRadius: 2      # Radius of the donkey's ice path
```

- `UpdateDelayTicks`: decides how often the plugin checks and updates necessary features, including world border adjustments, ice path creation, and donkey location.
- `BorderRadius`: Sets the initial border radius per player, which scales based on the number of online players.
- `IcePathRadius`: Specifies the radius of the donkey's icy path.

## Contributing

Feel free to contribute to this project by opening issues or submitting pull requests.

## License

This plugin is released under the [MIT License](LICENSE).
