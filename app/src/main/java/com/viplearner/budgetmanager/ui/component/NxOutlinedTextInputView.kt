package com.viplearner.budgetmanager.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun NxOutlinedTextInputView(
//    modifier: Modifier = Modifier,
//    label: String,
//    readOnly: Boolean = false,
//    errorMessage: String = "",
//    placeholder: String? = null,
//    leadingIcon: @Composable (() -> Unit)? = null,
//    value: TextFieldValue,
//    maxLength: Int = Int.MAX_VALUE,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    visualTransformation: VisualTransformation = VisualTransformation.None,
//    keyboardOptions: KeyboardOptions = KeyboardOptions(
//        keyboardType = KeyboardType.Text,
//        imeAction = ImeAction.Done
//    ),
//    trailingIcon: @Composable (() -> Unit) ?= null,
//    onValueChanged: (TextFieldValue) -> Unit
//) {
//    val keyboardState = LocalSoftwareKeyboardController.current
//
//    // This is to disable software keyboard for number entries when there's a physical keypad
//    // The user should use the physical keypad if there's one present
//    val inputTextService =
//        if(keyboardOptions.keyboardType == KeyboardType.Decimal ||
//            keyboardOptions.keyboardType == KeyboardType.Number ||
//            keyboardOptions.keyboardType == KeyboardType.NumberPassword){
//            when (Build.MANUFACTURER) {
//                "Morefun" -> null
//                "Kozen" -> LocalTextInputService.current
//                else -> LocalTextInputService.current
//            }
//        } else LocalTextInputService.current
//
//    CompositionLocalProvider(
//        LocalTextInputService provides inputTextService
//    ){
//        Column(
//            modifier = modifier
//                .fillMaxWidth(),
//        ) {
//            Text(
//                text = label,
//                style = MaterialTheme.typography.labelLarge,
//                color = nombaBlack,
//                fontWeight = FontWeight.Medium,
//                textAlign = TextAlign.Start
//            )
//
//            Spacer(modifier = Modifier.padding(vertical = 4.dp))
//            NxOutlinedTextField(
//                modifier = Modifier
//                    .height(50.dp)
//                    .fillMaxWidth(),
//                value = value,
//                readOnly = readOnly,
//                onValueChange = {
//                    onChangeValue(maxLength, it, onValueChanged)
//                },
//                textStyle = MaterialTheme.typography.body1.copy(color = nombaBlack),
//                placeholder = {
//                    PlaceholderText(placeholder)
//                },
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedContainerColor = nombaWhite,
//                    cursorColor = nombaBlack,
//                    disabledLabelColor = nombaGray,
//                    focusedTrailingIconColor = nombaGray,
//                    unfocusedTrailingIconColor = nombaGray,
//                    focusedBorderColor = nombaBlack
//                ),
//                visualTransformation = visualTransformation,
//                keyboardOptions = keyboardOptions,
//                trailingIcon = trailingIcon,
//                shape = RoundedCornerShape(2.dp),
//                singleLine = true,
//                leadingIcon = leadingIcon,
//                interactionSource = interactionSource,
//                keyboardActions = KeyboardActions(onDone = {
//                    keyboardState?.hide()
//                })
//            )
//            AnimatedVisibility(visible = errorMessage.isNotEmpty()) {
//                Text(
//                    text = errorMessage,
//                    style = MaterialTheme.typography.labelSmall.copy(
//                        color = nombaRed,
//                        fontSize = 11.sp,
//                        fontWeight = FontWeight.Medium
//                    ),
//                    textAlign = TextAlign.Start
//                )
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun NxOutlinedTextInputView(
//    modifier: Modifier = Modifier,
//    label: String,
//    readOnly: Boolean = false,
//    errorMessage: String = "",
//    placeholder: String? = null,
//    leadingIcon: @Composable (() -> Unit)? = null,
//    value: String,
//    maxLength: Int = Int.MAX_VALUE,
//    onValueChanged: (String) -> Unit,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    visualTransformation: VisualTransformation = VisualTransformation.None,
//    keyboardOptions: KeyboardOptions = KeyboardOptions(
//        keyboardType = KeyboardType.Text,
//        imeAction = ImeAction.Done
//    ),
//    trailingIcon: @Composable (() -> Unit)? = null,
//){
//    val keyboardState = LocalSoftwareKeyboardController.current
//
//    // This is to disable software keyboard for number entries when there's a physical keypad
//    // The user should use the physical keypad if there's one present
//    val inputTextService =
//        if(keyboardOptions.keyboardType == KeyboardType.Decimal ||
//            keyboardOptions.keyboardType == KeyboardType.Number ||
//            keyboardOptions.keyboardType == KeyboardType.NumberPassword){
//            when (Build.MANUFACTURER) {
//                "Morefun" -> null
//                "Kozen" -> LocalTextInputService.current
//                else -> LocalTextInputService.current
//            }
//        } else LocalTextInputService.current
//
//    CompositionLocalProvider(
//        LocalTextInputService provides inputTextService
//    ){
//        Column(
//            modifier = modifier
//                .fillMaxWidth(),
//        ) {
//            Text(
//                text = label,
//                style = MaterialTheme.typography.button,
//                color = nombaBlack,
//                fontWeight = FontWeight.Medium,
//                textAlign = TextAlign.Start
//            )
//
//            Spacer(modifier = Modifier.padding(vertical = 4.dp))
//            NxOutlinedTextField(
//                modifier = Modifier
//                    .height(50.dp)
//                    .fillMaxWidth(),
//                value = value,
//                readOnly = readOnly,
//                onValueChange = {
//                    onChangeStringValue(maxLength, it, onValueChanged)
//                },
//                textStyle = MaterialTheme.typography.body1.copy(color = nombaBlack),
//                placeholder = {
//                    PlaceholderText(placeholder)
//                },
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedContainerColor = nombaWhite,
//                    cursorColor = nombaBlack,
//                    disabledLabelColor = nombaGray,
//                    focusedTrailingIconColor = nombaGray,
//                    unfocusedTrailingIconColor = nombaGray,
//                    focusedBorderColor = nombaBlack
//                ),
//                visualTransformation = visualTransformation,
//                keyboardOptions = keyboardOptions,
//                trailingIcon = trailingIcon,
//                shape = RoundedCornerShape(2.dp),
//                singleLine = true,
//                leadingIcon = leadingIcon,
//                interactionSource = interactionSource,
//                keyboardActions = KeyboardActions(onDone = {
//                    keyboardState?.hide()
//                })
//            )
//            AnimatedVisibility(visible = errorMessage.isNotEmpty()) {
//                Text(
//                    text = errorMessage,
//                    style = MaterialTheme.typography.caption.copy(
//                        color = nombaRed,
//                        fontSize = 11.sp,
//                        fontWeight = FontWeight.Medium
//                    ),
//                    textAlign = TextAlign.Start
//                )
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun NxOutlinedTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    readOnly: Boolean = false,
//    textStyle: TextStyle = LocalTextStyle.current,
//    innerPaddingValues: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
//    label: @Composable (() -> Unit)? = null,
//    placeholder: @Composable (() -> Unit)? = null,
//    leadingIcon: @Composable (() -> Unit)? = null,
//    trailingIcon: @Composable (() -> Unit)? = null,
//    prefix: @Composable (() -> Unit)? = null,
//    suffix: @Composable (() -> Unit)? = null,
//    supportingText: @Composable (() -> Unit)? = null,
//    isError: Boolean = false,
//    visualTransformation: VisualTransformation = VisualTransformation.None,
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    keyboardActions: KeyboardActions = KeyboardActions.Default,
//    singleLine: Boolean = false,
//    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
//    minLines: Int = 1,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    shape: Shape = OutlinedTextFieldDefaults.shape,
//    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
//){
//    // If color is not provided via the text style, use content color as a default
//    val textColor = textStyle.color.takeOrElse {
//        androidx.compose.material3.MaterialTheme.colorScheme.onBackground
//    }
//    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
//
//    CompositionLocalProvider(
//        LocalTextSelectionColors provides
//            TextSelectionColors(backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background, handleColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer)
//    ) {
//        BasicTextField(
//            value = value,
//            modifier = if (label != null) {
//                modifier
//                    // Merge semantics at the beginning of the modifier chain to ensure padding is
//                    // considered part of the text field.
//                    .semantics(mergeDescendants = true) {}
//            } else {
//                modifier
//            }
//                .defaultMinSize(
//                    minWidth = OutlinedTextFieldDefaults.MinWidth,
//                    minHeight = OutlinedTextFieldDefaults.MinHeight
//                ),
//            onValueChange = onValueChange,
//            enabled = enabled,
//            readOnly = readOnly,
//            textStyle = mergedTextStyle,
//            cursorBrush = SolidColor(androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer),
//            visualTransformation = visualTransformation,
//            keyboardOptions = keyboardOptions,
//            keyboardActions = keyboardActions,
//            interactionSource = interactionSource,
//            singleLine = singleLine,
//            maxLines = maxLines,
//            minLines = minLines,
//            decorationBox = @Composable { innerTextField ->
//                OutlinedTextFieldDefaults.DecorationBox(
//                    value = value,
//                    visualTransformation = visualTransformation,
//                    innerTextField = innerTextField,
//                    placeholder = placeholder,
//                    label = label,
//                    leadingIcon = leadingIcon,
//                    trailingIcon = trailingIcon,
//                    prefix = prefix,
//                    suffix = suffix,
//                    supportingText = supportingText,
//                    singleLine = singleLine,
//                    enabled = enabled,
//                    isError = isError,
//                    interactionSource = interactionSource,
//                    colors = colors,
//                    contentPadding = innerPaddingValues,
//                    container = {
//                        OutlinedTextFieldDefaults.ContainerBox(
//                            enabled,
//                            isError,
//                            interactionSource,
//                            colors,
//                            shape
//                        )
//                    }
//                )
//            }
//        )
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    innerPaddingValues: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
){
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        androidx.compose.material3.MaterialTheme.colorScheme.onBackground
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))


    CompositionLocalProvider(
        LocalTextSelectionColors provides
                TextSelectionColors(backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background, handleColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        BasicTextField(
            value = value,
            modifier = if (label != null) {
                modifier
                    // Merge semantics at the beginning of the modifier chain to ensure padding is
                    // considered part of the text field.
                    .semantics(mergeDescendants = true) {}
            } else {
                modifier
            }
                .defaultMinSize(
                    minWidth = OutlinedTextFieldDefaults.MinWidth,
                    minHeight = OutlinedTextFieldDefaults.MinHeight
                ),
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = mergedTextStyle,
            cursorBrush = SolidColor(androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            decorationBox = @Composable { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = value,
                    visualTransformation = visualTransformation,
                    innerTextField = innerTextField,
                    placeholder = placeholder,
                    label = label,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    prefix = prefix,
                    suffix = suffix,
                    supportingText = supportingText,
                    singleLine = singleLine,
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    contentPadding = innerPaddingValues,
                    container = {
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled,
                            isError,
                            interactionSource,
                            colors,
                            shape
                        )
                    }
                )
            }
        )
    }
}

//@Preview(device = "spec:width=480px,height=692px,dpi=240", showBackground = true)
//@Composable
//fun NxOutlinedTextInputViewPreview(){
//    var value by remember {
//        mutableStateOf("")
//    }
//    NxOutlinedTextInputView(
//        modifier = Modifier.fillMaxWidth(),
//        label = "Input Pin",
//        value = value,
//        maxLength = 4,
//        placeholder = "Input Nomba Pin",
//        onValueChanged = {value = it},
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//        trailingIcon = {
//            IconButton(onClick = { value = "" }) {
//                Icon(
//                    imageVector = Icons.Rounded.Visibility,
//                    tint = nombaDarkerGray,
//                    contentDescription = null,
//                )
//            }
//        },
//        errorMessage = "Error message"
//    )
//}
//@Composable
//internal fun PlaceholderText(placeholder: String?) {
//    if (placeholder != null) {
//        Text(
//            text = placeholder,
//            color = Color.Gray,
//            style = MaterialTheme.typography.button,
//            modifier = Modifier.fillMaxWidth()
//        )
//    }
//}
//
//
//private fun onChangeValue(
//    maxLength: Int,
//    it: TextFieldValue,
//    onValueChanged: (TextFieldValue) -> Unit
//) {
//    if (it.text.length <= maxLength) {
//        onValueChanged(it)
//    }
//}
//
//
//private fun onChangeStringValue(
//    maxLength: Int,
//    it: String,
//    onStringValueChanged: (String) -> Unit
//) {
//    if (it.length <= maxLength) {
//        onStringValueChanged(it)
//    }
//}