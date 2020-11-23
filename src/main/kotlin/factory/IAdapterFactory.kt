package factory

import ICryonomyAdapter

interface IAdapterFactory {
    fun create(): ICryonomyAdapter
}