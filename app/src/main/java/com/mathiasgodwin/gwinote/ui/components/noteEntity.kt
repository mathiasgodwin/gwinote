import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.mathiasgodwin.gwinote.data.local.entity.NoteEntity

@Composable
fun Note(
 note: NoteEntity? = null
) {
    val backgroundShape: Shape = RoundedCornerShape(4.dp);
    val typo = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .background(color = colorScheme.surface, backgroundShape),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                NoteColor(
                    color = if (note != null) Color(note.color.hex.toColorInt()) else Color.Red,
                    size = 40.dp,
                    border = 1.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        note?.title ?: "Title",
                        style = typo.bodyMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            letterSpacing = 0.15.sp,
                        )
                    )
                    Text(
                        note?.content ?: "Content     @Insert(onConflict = OnConflictStrategy.ABORT) // Default behavior, will throw error if ID exists",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = typo.bodyMedium.copy(
                            color = colorScheme.onSurface.copy(alpha = .75f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            letterSpacing = .25.sp,
                        ),
                    )
                }
            }
            Checkbox(
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun NotePreview() {
    Note()
}

@Composable
private fun NoteColor(
    color: Color,
    size: Dp,
    padding: Dp = 0.dp,
    border: Dp,
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .size(size)
            .clip(CircleShape)
            .background(color)
            .border(
                BorderStroke(
                    border,
                    SolidColor(Color.Black)
                ),
                CircleShape
            )
    )
}

@Composable
fun NoteColorPreview() {
    NoteColor(
        color = Color.Blue,
        size = 24.dp,
        border = 1.dp
    )
}

