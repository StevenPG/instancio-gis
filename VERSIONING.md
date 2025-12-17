# VERSIONS.md

## Versioning Scheme

This project uses a custom semantic versioning approach tied to an external dependency:

- **MAJOR** (1st number): Overall Compatibility Level
- **MINOR** (2nd number): Dependency library version
- **PATCH** (3rd number): Project-specific patches and fixes

### Example Mapping

| Our Version | Dependency Version | Notes |
|---|---|---|
| 1.0.0 | 3.2.0 | Initial release |
| 1.1.0 | 3.3.0 | Bump on dependency minor release |
| 1.1.1 | 3.3.0 | Patch for our changes only |

## Release History

### 1.0.0
- Initial release

## Compatibility Matrix

| Project Version | Name | Dependency Version | Status |
|---|---|---|---|
| 1.0.0 | locationtech-jts core |1.20.0 | Supported |

## Upgrade Guide

When the dependency releases a new minor version (e.g., 3.2 → 3.3), bump our MINOR version accordingly. For patch releases of the dependency, no action is needed unless we introduce our own fixes.

---

**Last Updated:** December 16, 2025
