/**
 * Created by smallufo on 2020-03-08.
 */
package destiny.tools

@Target(AnnotationTarget.CLASS)
annotation class Impl(val value: String, val default: Boolean = false)
