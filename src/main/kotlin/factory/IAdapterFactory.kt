package factory

import CryonomyAdapter

interface IAdapterFactory {
    fun create(): CryonomyAdapter
}