package com.mathiasgodwin.gwinote.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mathiasgodwin.gwinote.navigations.Screen
import com.mathiasgodwin.gwinote.ui.theme.GwinoteTheme
import kotlin.reflect.KClass


@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToTrash: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxWidth()) {
        AppDrawerHeader()
        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = .2f))
        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = "Notes",
            isSelected = currentRoute == Screen.Notes.route,
            onClick = {
                navigateToHome()
                closeDrawer()
            }
        )
        ScreenNavigationButton(
            icon = Icons.Filled.Delete,
            label = "Trash",
            isSelected = currentRoute == Screen.Trash.route,
            onClick = {
                navigateToTrash()
                closeDrawer()
            }
        )
        Spacer(Modifier.height(16.dp))
        LightDarkThemeItem()

    }
}


@Composable
private fun AppDrawerHeader() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Drawer Header Icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.padding(16.dp),
        )
        Text(
            "Gwinote",
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
        )
    }
}

@Composable
private fun ScreenNavigationButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {

    val colors = MaterialTheme.colorScheme

    val imageAlpha = if (isSelected) 1f else .6f

    val textColor = if (isSelected) colors.primary else colors.onSurface.copy(alpha = .6f)
    val backgroundColor = if (isSelected) colors.primary.copy(alpha = .12f) else colors.surface

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Image(
                imageVector = icon,
                contentDescription = "Screen Navigation Button",
                colorFilter = ColorFilter.tint(textColor),
                alpha = imageAlpha
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
        }
    }
}

@Composable
private fun LightDarkThemeItem() {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Turn on dark theme",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
        Switch(
            checked = false, onCheckedChange = {
            },
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=1440px,height=2560px,dpi=560,navigation=buttons",
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun PreviewAllComponentsPreview () {
    GwinoteTheme {
        Column {
            AppDrawerHeader()
            ScreenNavigationButton(
                icon = Icons.Filled.Home,
                label = "Notes",
                isSelected = true,
                onClick = {
                    println("Clicked on Notes")
                }
            )
            LightDarkThemeItem()
        }

    }
}
@Preview(
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=1440px,height=2560px,dpi=560,navigation=buttons",
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun LightDarkThemeItemPreview() {
    GwinoteTheme {
        LightDarkThemeItem()
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=1440px,height=2560px,dpi=560,navigation=buttons",
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun AppDrawerHeaderPreview() {
    GwinoteTheme {
        AppDrawerHeader()
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=1440px,height=2560px,dpi=560,navigation=buttons",
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun ScreenNavigationButtonPreview() {
    GwinoteTheme {
        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = "Notes",
            isSelected = true,
            onClick = {
                println("Clicked on Notes")
            }
        )
    }
}