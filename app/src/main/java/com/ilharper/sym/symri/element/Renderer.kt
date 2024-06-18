package com.ilharper.sym.symri.element

import android.content.Context
import android.view.View
import android.widget.ImageView
import coil.ImageLoader
import coil.request.ImageRequest
import com.google.android.material.textview.MaterialTextView
import com.ilharper.sym.R
import com.ilharper.symri.element.SatoriElement
import com.ilharper.symri.element.SatoriImg
import com.ilharper.symri.element.SatoriText
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Renderer
    @Inject
    constructor(
        private val imageLoader: ImageLoader,
    ) {
        fun render(
            context: Context,
            elements: List<SatoriElement>?,
        ): List<View> {
            if (elements.isNullOrEmpty()) return listOf()
            return renderIntl(context, elements) ?: unsupported(context)
        }

        private fun renderIntl(
            context: Context,
            elements: List<SatoriElement>,
        ): List<View>? {
            val result = elements.map { visit(context, it) }
            return if (result.all { it == null }) {
                null
            } else {
                result.flatMap {
                    it // ?: unsupported(context)
                }
            }
        }

        private fun visit(
            context: Context,
            element: SatoriElement,
        ): List<View> =
            // Return type should have nullable('?') mark
            when (element) {
                is SatoriText ->
                    listOf(
                        MaterialTextView(context).apply {
                            text = element.text
                        },
                    )

                is SatoriImg ->
                    listOf(
                        ImageView(context).also {
                            it.minimumWidth =
                                context.resources.getDimensionPixelOffset(R.dimen.renderer_image_min_size)
                            it.minimumHeight =
                                context.resources.getDimensionPixelOffset(R.dimen.renderer_image_min_size)

                            imageLoader.enqueue(
                                ImageRequest
                                    .Builder(context)
                                    .allowHardware(false) // This ImageView (↑) is software-rendered
                                    .data(element.src)
                                    .target(it)
                                    .crossfade(true)
                                    .build(),
                            )
                        },
                    )

                else -> render(context, element.children)
            }

        private fun unsupported(context: Context) =
            listOf(
                MaterialTextView(context).apply {
                    text = "[不支持的消息]"
                },
            )
    }
