import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import javax.swing.*
import javax.swing.border.EmptyBorder
import javax.swing.filechooser.FileNameExtensionFilter

class MarkdownViewer : JFrame(), ActionListener {
  private val textArea: JTextArea = JTextArea()
  private val loadButton: JButton = JButton("Load Markdown File")
  private val titleLabel: JLabel = JLabel("This is a simple application for viewing markdown raw file. Please select a .md file to load", SwingConstants.CENTER)


  init {
    title = "Markdown Viewer"
    size = Dimension(600, 400)
    defaultCloseOperation = EXIT_ON_CLOSE
    layout = BorderLayout()

    textArea.isEditable = true
    textArea.lineWrap = true
    textArea.wrapStyleWord = true
    textArea.border = EmptyBorder(10, 10, 10, 10)

    val scrollPane = JScrollPane(textArea)
    scrollPane.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS

    loadButton.addActionListener(this)
    loadButton.border = EmptyBorder(10, 10, 10, 10)

    add(titleLabel, BorderLayout.NORTH)
    add(scrollPane, BorderLayout.CENTER)
    add(loadButton, BorderLayout.SOUTH)
  }

  override fun actionPerformed(e: ActionEvent?) {
    val fileChooser = JFileChooser()
    fileChooser.fileFilter = FileNameExtensionFilter("Markdown files", "md")

    val returnValue = fileChooser.showOpenDialog(this)
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      val selectedFile: File = fileChooser.selectedFile
      if (selectedFile.extension == "md") {
        val content = selectedFile.readText()
        textArea.text = content
      } else {
        JOptionPane.showMessageDialog(this, "Please select a valid Markdown file.", "Invalid File", JOptionPane.ERROR_MESSAGE)
      }
    }
  }
}

fun main() {
  SwingUtilities.invokeLater {
    val viewer = MarkdownViewer()
    viewer.isVisible = true
  }
}