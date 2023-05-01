import me.naotiki.ese.core.EseSystem
import me.naotiki.ese.core.api.EsePlugin
import me.naotiki.ese.core.commands.parser.Executable
import me.naotiki.ese.core.user.User
import me.naotiki.ese.core.vfs.Path.Companion.toPath
import me.naotiki.ese.core.vfs.dsl.dir
import me.naotiki.ese.core.vfs.dsl.executable
import me.naotiki.ese.core.vfs.dsl.fileDSL
import me.naotiki.ese.core.vfs.toDirectoryOrNull

class ExamplePlugin:EsePlugin {
    override suspend fun init(user: User) {
        // Find /opt dir
        val installDir = EseSystem.FileTree.tryResolve("/opt".toPath())?.toDirectoryOrNull()
            ?: fileDSL(EseSystem.FileTree.root, user) {
                //Create /opt dir
                dir("/opt")
            }
        fileDSL(installDir, user) {
            //Create Directory (You can change name as you like)
            dir("ExamplePlugin") {
                //Register command
                EseSystem.FileTree.executableEnvPaths += dir("bin") {
                    executable(PluginCommand())
                }
            }
        }
        EseSystem.IO.printChannel.println("インストール完了\nHello, ${user.name}")
    }
}
// Example Command
class PluginCommand : Executable<Unit>("example", "ExamplePluginプラグインによって追加されたコマンド") {
    override suspend fun execute(user: User, rawArgs: List<String>) {
        out.println("ExamplePluginより")
    }
}